(defproject com.stuartsierra/dependency "0.1.3-SNAPSHOT"
  :description "A data structure for representing dependency graphs"
  :url "https://github.com/stuartsierra/dependency"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojurescript "0.0-2322"]]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.6.0"]]}
             :clj-1.5.0 {:dependencies [[org.clojure/clojure "1.5.0"]]}
             :clj-1.4.0 {:dependencies [[org.clojure/clojure "1.4.0"]]}
             :clj-1.3.0 {:dependencies [[org.clojure/clojure "1.3.0"]]}}
  :plugins [[com.keminglabs/cljx "0.4.0" :exclusions [org.clojure/clojure]]
            [lein-cljsbuild "1.0.3"]
            [com.cemerick/clojurescript.test "0.3.0"]]
  :hooks [leiningen.cljsbuild cljx.hooks]
  :source-paths ["src" "target/gen/src"]
  :test-paths ["test" "target/gen/test"]
  :cljx {:builds [{:source-paths ["src/"]  :output-path "target/gen/src"  :rules :clj}
                  {:source-paths ["src/"]  :output-path "target/gen/src"  :rules :cljs}
                  {:source-paths ["test/"] :output-path "target/gen/test" :rules :clj}
                  {:source-paths ["test/"] :output-path "target/gen/test" :rules :cljs}]}
  :cljsbuild {:builds {:dev {:source-paths ["target/gen/src"]
                             :compiler {:output-to "target/gen/js/main.js"
                                        :output-dir "target/gen/js"
                                        :optimizations :whitespace
                                        :pretty-print true
                                        :source-map "target/gen/js/main.js.map"}}
                       :test {:source-paths ["target/gen/src" "target/gen/test"]
                              :compiler {:output-to "target/gen/test/js/testable.js"
                                         ;; :optimizations :advanced
                                         :optimizations :simple
                                         :pretty-print true}}}
              :test-commands {"phantomjs" ["phantomjs"
                                           :runner
                                           "test/bind-polyfill.js"
                                           "target/gen/test/js/testable.js"]}})
