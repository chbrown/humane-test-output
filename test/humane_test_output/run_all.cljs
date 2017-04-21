(ns humane-test-output.run-all
  (:require [cljs.test :refer-macros [run-all-tests run-tests]]
            [humane-test-output.core]
            [humane-test-output.test.formatting-test]
            [humane-test-output.test.records-test]))

(enable-console-print!)

(defn ^:export run []
  (run-all-tests #"humane-test-output.test.*-test"))

