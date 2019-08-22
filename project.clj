(defproject juxt/muuntaja "0.6.4"
  :description "Clojure library for format encoding, decoding and content-negotiation"
  :url "https://github.com/juxt/muuntaja"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v20.html"}
  :javac-options ["-Xlint:unchecked" "-target" "1.7" "-source" "1.7"]
  :java-source-paths ["src/java"]
  :managed-dependencies [[json/muuntaja "0.6.4"]
                         [metosin/jsonista "0.2.3"]
                         [com.cognitect/transit-clj "0.8.313"]
                         [cheshire "5.8.1"]
                         [clj-commons/clj-yaml "0.7.0"]
                         [clojure-msgpack "1.2.1" :exclusions [org.clojure/clojure]]]
  :dependencies []
  :source-paths ["modules/muuntaja/src"]
  :plugins [[lein-codox "0.10.6"]]
  :codox {:src-uri "http://github.com/metosin/muuntaja/blob/master/{filepath}#L{line}"
          :output-path "doc"
          :metadata {:doc/format :markdown}}
  :scm {:name "git"
        :url "https://github.com/metosin/muuntaja"}
  :deploy-repositories [["releases" :clojars]]
  :profiles {:dev {:jvm-opts ^:replace ["-server"]

                   ;; all module sources for development
                   :source-paths ["modules/muuntaja-cheshire/src"
                                  "modules/muuntaja-yaml/src"
                                  "modules/muuntaja-msgpack/src"]

                   :dependencies [[org.clojure/clojure "1.10.1"]
                                  [ring/ring-core "1.7.1"]
                                  [ring-middleware-format "0.7.4"]
                                  [ring-transit "0.1.6"]
                                  [ring/ring-json "0.4.0"]

                                  ;; modules
                                  [juxt/muuntaja "0.6.4"]
                                  [metosin/muuntaja-cheshire "0.6.4"]
                                  [metosin/muuntaja-msgpack "0.6.4"]
                                  [metosin/muuntaja-yaml "0.6.4"]

                                  ;; Sieppari
                                  [metosin/sieppari "0.0.0-alpha5"]

                                  ;; Pedestal
                                  [org.clojure/core.async "0.4.490" :exclusions [org.clojure/tools.reader]]
                                  [io.pedestal/pedestal.service "0.5.5" :exclusions [org.clojure/tools.reader
                                                                                     org.clojure/core.async
                                                                                     org.clojure/core.memoize]]
                                  [javax.servlet/javax.servlet-api "4.0.1"]
                                  [org.slf4j/slf4j-log4j12 "1.7.26"]

                                  [criterium "0.4.5"]]}
             :1.7 {:dependencies [[org.clojure/clojure "1.7.0"]]}
             :1.8 {:dependencies [[org.clojure/clojure "1.8.0"]]}
             :1.9 {:dependencies [[org.clojure/clojure "1.9.0"]]}
             :perf {:jvm-opts ^:replace ["-server"
                                         "-Xmx4096m"
                                         "-Dclojure.compiler.direct-linking=true"]}
             :analyze {:jvm-opts ^:replace ["-server"
                                            "-Dclojure.compiler.direct-linking=true"
                                            "-XX:+PrintCompilation"
                                            "-XX:+UnlockDiagnosticVMOptions"
                                            "-XX:+PrintInlining"]}}
  :aliases {"all" ["with-profile" "dev:dev,1.7:dev,1.8:dev,1.9"]
            "perf" ["with-profile" "default,dev,perf"]
            "analyze" ["with-profile" "default,dev,analyze"]})
