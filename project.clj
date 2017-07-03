(defproject pong "0.1.0-SNAPSHOT"
  :description "clojure pong"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.542"]]
  :main ^:skip-aot pong.core
  :target-path "target/%s"
  :plugins [[lein-figwheel "0.5.8"]
            [lein-cljsbuild "1.1.6"]]
  :cljsbuild {
              :builds [{:id "dev"
                        :source-paths ["src"]
                        :figwheel true
                        :compiler {:main "pong.core"}
                        }]
              }
  :profiles {:uberjar {:aot :all}})
