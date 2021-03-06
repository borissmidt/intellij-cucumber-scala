import io.cucumber.scala.ScalaDsl

class StepDefinitions extends ScalaDsl {
  val calc = new Calculator

  When("^I add (\\d+) and (\\d+)$") { (arg1: Double, arg2: Double) =>
    calc push arg1
    calc push arg2
    calc push "+"
  }

  When("I sub (\\d+)" + " and " + "(\\d+)") {
    (arg1: Double, arg2: Double) =>
      calc push arg1
      calc push arg2
      calc push "-"
  }

  When("I div " + (5 + 5) + " by " + (10 - 8)) {
    calc push 10.0
    calc push 2.0
    calc push "/"
  }

  And("nothing else") {

  }

  And("Some {int} parameter with {float}") {

  }

  Then("^the result is ([+-]?\\d+)$") { expected: Double =>
    assertEquals(expected, calc.value, 0.001)
  }
}