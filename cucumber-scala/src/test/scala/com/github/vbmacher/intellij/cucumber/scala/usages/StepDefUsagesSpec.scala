package com.github.vbmacher.intellij.cucumber.scala.usages

import com.github.vbmacher.intellij.cucumber.scala.ScCucumberSpecBase
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StepDefUsagesSpec extends ScCucumberSpecBase {

  test("find usages") {
    loadTestCase("usages/StepDefinitions.scala", "usages/testcase1.feature", "usages/testcase2.feature")
    val usages = myFixture.testFindUsagesUsingAction(getTestDataPath + "/usages/StepDefinitions.scala")
    assert(usages.size() == 2)
  }
}
