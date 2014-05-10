(ns shortener.core
  (:use [compojure.core]
        [ring.adapter.jetty :only (run-jetty)])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [net.cgrand.enlive-html :as html]
            [ring.util.response :as response])
  (:gen-class))


(defonce index (atom 10000))
(defonce urls (atom {}))

(defn num2id [x]  (Long/toString x 36))

(defn gen-id! [] (num2id (swap! index inc)))

(defn shorten
  "Generate a short id for url"
  [url]
  (let [id (gen-id!)]
    (swap! urls assoc id url)
    id))

(defn concat-url
  [id] (str "http://localhost:8888/" id))

(html/deftemplate main "index.html" [])

(defn redirect
  [id]
  (response/redirect (@urls id)))

(defroutes app
  (GET "/" [] (main))
  (POST "/shorten" {params :params} (->> (:user_url params)
                                         (shorten)
                                         (concat-url)))
  (GET "/:id" [id] (redirect id))
  (route/not-found "<h1>Page not found</h1>"))


(defn runServer
  [port join]
  (run-jetty (handler/site app) {:port port :join? join}))


;;;below 2 forms are for interactive programming demo ...
;;(def server (runServer 8888 false))

;;(.stop server)

(defn -main
  [& args]
   (runServer 8888 false))
