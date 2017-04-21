(ns humane-test-output.core
  (:require [cljs.test :refer-macros [run-all-tests run-tests]]
            [cljs.pprint :as pp]
            [humane-test-output.macro :refer [do-report]]
            [humane-test-output.util :as util])
  (:require-macros [humane-test-output.assert-expr]))

(def pprint-map (get-method pp/simple-dispatch :map))

(defmethod pp/simple-dispatch :map [amap]
  (if (record? amap)
    (util/pprint-record amap)
    (pprint-map amap)))

(util/define-fail-report)
