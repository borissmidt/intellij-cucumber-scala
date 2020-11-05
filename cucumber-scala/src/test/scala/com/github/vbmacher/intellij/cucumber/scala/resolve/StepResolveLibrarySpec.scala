package com.github.vbmacher.intellij.cucumber.scala.resolve

import com.github.vbmacher.intellij.cucumber.scala.{LibraryLightProjectDescriptor, LocalDependency}
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StepResolveLibrarySpec extends StepResolveSpecBase {

  override val DESCRIPTOR = new LibraryLightProjectDescriptor(
    LocalDependency(
      "example-stepdefs-lib",
      classOf[StepResolveLibrarySpec].getResource("/example-stepdefs-lib/example-stepdefs-lib-1.0.0.jar"),
      classOf[StepResolveLibrarySpec].getResource("/example-stepdefs-lib/example-stepdefs-lib-1.0.0-sources.jar")
    )
  )

  private val checkResolveDirect = singleResolve("stepsInLibrary/testcase.feature") _

  test("resolve simple") {
    checkResolveDirect("I add 5 and 6")
  }
}
