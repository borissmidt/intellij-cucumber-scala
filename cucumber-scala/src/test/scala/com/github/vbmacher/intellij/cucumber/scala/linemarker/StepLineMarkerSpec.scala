package com.github.vbmacher.intellij.cucumber.scala.linemarker

import com.github.vbmacher.intellij.cucumber.scala.ScCucumberSpecBase
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StepLineMarkerSpec extends ScCucumberSpecBase {

  test("line marker positive") {
    loadTestCase("lineMarker/StepDefinition.scala")
    val lineMarkers = findLineMarkers()

    assert(lineMarkers.size === 1)
  }

  test("line marker negative") {
    loadTestCase("lineMarker/NotAStepDefinition.scala")
    val lineMarkers = findLineMarkers()

    assert(lineMarkers.isEmpty)
  }
}
