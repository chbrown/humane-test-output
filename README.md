# Humane test output for clojure.test

This library does two things:

1. Test output is pretty printed.
2. Equality assertions are also diffed.

Version 0.8.0 and later of humane-test-output requires Clojure 1.8.0 or later.


## IDEs

This test output formatting works great with Emacs and cider 0.10 or lower, and when running Leiningen in a console. However, some IDEs are also bashing in their own versions of test formatting.

I do not recommend using this with cider 0.11.0 or greater or with Cursive, or LightTable, or any other IDE.


## Clojure

To activate it you will need to call `humane-test-output.core/activate!`.
The preferred way to do this with Leiningen is to use an injection in the :user profile in your `~/.lein/profiles.clj`:

```clojure
{:user {:dependencies [[chbrown/humane-test-output "0.8.3"]]
        :injections [(require 'humane-test-output.core)
                     (humane-test-output.core/activate!)]}}
```

If you are on a mixed team and some members need to disable humane test output, they can define the `INHUMANE_TEST_OUTPUT` environment variable, though again it's better for each user to enable it in their own `~/.lein/profiles.clj`.

Once humane-test-output is activated, instead of this:

```clojure
FAIL in (a-test) (humane_test_output_test.clj:7)
FIXME, I fail.
 expected: (= {:foo :bar, :baz :quux, :something "a long string?", :another-key "and another value"} {:fo :bar, :baz :quux, :something "a long string?", :another-key "and another value"})
  actual: (not (= {:another-key "and another value", :foo :bar, :something "a long string?", :baz :quux} {:another-key "and another value", :something "a long string?", :fo :bar, :baz :quux}))

FAIL in (a-test) (humane_test_output_test.clj:11)
FIXME, I fail.
expected: (= {:foo :bar, :baz :quux} {:foo :bar, :baz :quux} {:fo :bar, :baz :quux})
  actual: (not (= {:foo :bar, :baz :quux} {:foo :bar, :baz :quux} {:fo :bar, :baz :quux}))
  FAIL in (a-test) (humane_test_output_test.clj:14)

FIXME, I fail.
expected: (list? foo)
  actual: (not (list? {:another-key "and another value", :foo :bar, :something "a long string?", :baz :quux}))
```

You get this:

```clojure
FAIL in (a-test) (humane_test_output_test.clj:7)
FIXME, I fail.
expected: {:another-key "and another value",
           :foo :bar,
           :something "a long string?",
           :baz :quux}
  actual: {:fo :bar}
    diff: - {:baz :quux,
             :something "a long string?",
             :foo :bar,
             :another-key "and another value"}
          + {:fo :bar}

FAIL in (a-test) (humane_test_output_test.clj:10)
FIXME, I fail.
expected: {:foo :bar, :baz :quux}
  actual: {:fo :bar, :baz :quux}
    diff: - {:foo :bar}
          + {:fo :bar}

FAIL in (a-test) (humane_test_output_test.clj:13)
FIXME, I fail.
expected: (list? foo)
  actual: (not
           (list?
            {:another-key "and another value",
             :foo :bar,
             :something "a long string?",
             :baz :quux}))
```


## ClojureScript

It is not necessary to activate humane-test-output with ClojureScript, but the behavior should be much the same as with Clojure.


## License

Copyright © Paul Stadig and Outpace Systems, Inc. All rights reserved.

Some small bits of code were taken from clojure.test and modified, and are
Copyright © Rich Hickey. All rights reserved.

Distributed under the Eclipse Public License, the same as Clojure.


## Acknowledgements

I am grateful for design and implementation assistance from Bryce Covert.

Thanks to Miloslav Nenadál for the ClojureScript implementation.

Thanks to Paul Stadig for the upstream repo from which this is forked.
