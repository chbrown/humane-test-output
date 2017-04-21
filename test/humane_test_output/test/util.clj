(ns humane-test-output.test.util)

(defmacro deftest+
  [test-name expected actual]
  `(~'deftest ~test-name
     (~'is (= ~expected ~actual) "THIS ONE SHOULD ALSO FAIL")))
