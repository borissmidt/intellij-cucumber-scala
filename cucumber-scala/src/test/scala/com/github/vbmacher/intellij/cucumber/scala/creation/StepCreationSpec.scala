package com.github.vbmacher.intellij.cucumber.scala.creation

import io.cucumber.scala.ScalaSnippet.tripleDoubleQuotes
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StepCreationSpec extends StepCreationSpecBase {

  test("no params") {
    val files = loadTestCase("creation/noparams.feature", "creation/StepDefinitions.scala")
    checkStepCreation(files(0), files(1),
      s"""import io.cucumber.scala.ScalaDsl
        |
        |class StepDefinitions extends ScalaDsl {
        |
        |  When(${tripleDoubleQuotes}I do anything$tripleDoubleQuotes) { () =>
        |    // Write code here that turns the phrase above into concrete actions
        |    throw new io.cucumber.scala.PendingException()
        |  }
        |}""".stripMargin
    )
  }

  test("with params") {
    val files = loadTestCase("creation/params.feature", "creation/StepDefinitions.scala")
    checkStepCreation(files(0), files(1),
      s"""import io.cucumber.scala.ScalaDsl
        |
        |class StepDefinitions extends ScalaDsl {
        |
        |  When(${tripleDoubleQuotes}I sub {int} and {int}$tripleDoubleQuotes) { (int1: Int, int2: Int) =>
        |    // Write code here that turns the phrase above into concrete actions
        |    throw new io.cucumber.scala.PendingException()
        |  }
        |}""".stripMargin
    )
  }

  test("outline") {
    val files = loadTestCase("creation/outline.feature", "creation/StepDefinitions.scala")
    checkStepCreation(files(0), files(1),
      s"""import io.cucumber.scala.ScalaDsl
        |
        |class StepDefinitions extends ScalaDsl {
        |
        |  When(${tripleDoubleQuotes}I add {int} and {int}$tripleDoubleQuotes) { (int1: Int, int2: Int) =>
        |    // Write code here that turns the phrase above into concrete actions
        |    throw new io.cucumber.scala.PendingException()
        |  }
        |}""".stripMargin
    )
  }

  test("table") {
    val files = loadTestCase("creation/table.feature", "creation/StepDefinitions.scala")
    checkStepCreation(files(0), files(1),
      s"""import io.cucumber.scala.ScalaDsl
        |
        |class StepDefinitions extends ScalaDsl {
        |
        |  Given(${tripleDoubleQuotes}the following data$tripleDoubleQuotes) { (dataTable: io.cucumber.datatable.DataTable) =>
        |    // Write code here that turns the phrase above into concrete actions
        |    throw new io.cucumber.scala.PendingException()
        |  }
        |}""".stripMargin
    )
  }
}
