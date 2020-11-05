package com.github.vbmacher.intellij.cucumber.scala.resolve

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StepResolveSpec extends StepResolveSpecBase {
  private val checkResolveDirect = singleResolve("resolveDirect/testcase.feature", "resolveDirect/StepDefinitions.scala") _
  private val checkResolveIndirect = singleResolve("resolveIndirect/testcase.feature", "resolveIndirect/StepDefinitions.scala") _

  test("resolve simple") {
    checkResolveDirect("And nothing else")
  }

  test("resolve with parameters") {
    checkResolveDirect("I add 4 and 5")  // does not include "When", because test regex is within ^$
  }

  test("scala expression in name") {
    checkResolveDirect("When I div 10 by 2")
  }

  test("resolve indirect") {
    checkResolveIndirect("When I div 10 by 2")
  }

  test("resolve direct with cucumber parameters") {
    checkResolveDirect("Some 55 parameter with 3.14")
  }

  test("resolve multiple definitions") {
    multiResolve(2,
      "resolveMultiple/testcase.feature",
      "resolveMultiple/StepDefs1.scala",
      "resolveMultiple/StepDefs2.scala"
    )("the result is 9")
  }
}