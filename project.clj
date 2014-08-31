(defproject org.clojars.russpowers/dependency "0.1.3-SNAPSHOT"
  :description "A data structure for representing dependency graphs"
  :url "https://github.com/russpowers/dependency"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojurescript "0.0-2322"]
                 [org.clojure/clojure "1.6.0"]]

  :source-paths ["src" "target/classes"]
  :test-paths ["target/test-classes"]

  :cljx {:builds [{:source-paths ["src"]  :output-path "target/classes"  :rules :clj}
                  {:source-paths ["src"]  :output-path "target/classes"  :rules :cljs}
                  {:source-paths ["test"] :output-path "target/test-classes" :rules :clj}
                  {:source-paths ["test"] :output-path "target/test-classes" :rules :cljs}]}

  :profiles {:dev {:plugins
                   [[com.keminglabs/cljx "0.4.0" :exclusions [org.clojure/clojure]]
                    [lein-cljsbuild "1.0.3"]
                    [com.cemerick/clojurescript.test "0.3.1"]]
                   :aliases {"cleantest" ["do" "clean," "cljx" "once," "test," "cljsbuild" "test"]
                             "deploy" ["do" "clean," "cljx" "once," "deploy" "clojars"]}
                   :hooks [cljx.hooks]}}


  :cljsbuild {:builds [{:source-paths ["target/classes" "target/test-classes"]
                        :compiler {:output-to "target/testable.js"
                                   :optimizations :advanced
                                   :pretty-print true}}]
              :test-commands {"phantomjs" ["phantomjs"
                                           :runner
                                           "test/bind-polyfill.js"
                                           "target/gen/test/js/testable.js"]}})
