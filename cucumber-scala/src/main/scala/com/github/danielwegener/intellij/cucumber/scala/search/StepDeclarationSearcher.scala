package com.github.danielwegener.intellij.cucumber.scala.search

import com.github.danielwegener.intellij.cucumber.scala.ScCucumberUtil
import com.github.danielwegener.intellij.cucumber.scala.steps.ScStepDefinition
import com.intellij.lang.injection.InjectedLanguageManager
import com.intellij.pom.{PomDeclarationSearcher, PomTarget}
import com.intellij.psi.{PsiDocumentManager, PsiElement}
import com.intellij.psi.util.{CachedValueProvider, CachedValuesManager}
import com.intellij.util.Consumer
import org.jetbrains.plugins.scala.lang.psi.api.base.ScLiteral
import org.jetbrains.plugins.scala.lang.psi.api.expr.ScMethodCall

class StepDeclarationSearcher extends PomDeclarationSearcher {

  override def findDeclarationsAt(psiElement: PsiElement, offsetInElement: Int, consumer: Consumer[PomTarget]): Unit = {
    val host = InjectedLanguageManager.getInstance(psiElement.getProject).getInjectionHost(psiElement)

    // Currently works just for literal regexes
    val hostOrElement = Option(host).getOrElse(psiElement)

    hostOrElement.getParent match {

      case literal: ScLiteral =>
        for {
          parent <- Option(literal.getParent) //~literal
          pparent <- Option(parent.getParent) //(~literal)
          ppparent <- Option(pparent.getParent) //When(~literal)
        } yield ppparent match {
          case method: ScMethodCall if ScCucumberUtil.isStepDefinition(method) =>
            consumer.consume(getCachedStepDefinition(method))
          case _ =>
        }

      case _ =>
    }
  }

  private def getCachedStepDefinition(statement: ScMethodCall) = {
    CachedValuesManager.getCachedValue(statement, () => {
      val document = PsiDocumentManager.getInstance(statement.getProject).getDocument(statement.getContainingFile)
      CachedValueProvider.Result.create(ScStepDefinition(statement), document)
    })
  }
}
