/*
 * Copyright 2018 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers

import javax.inject.Inject

import play.api.data.Form
import play.api.i18n.{I18nSupport, MessagesApi}
import uk.gov.hmrc.play.bootstrap.controller.FrontendController
import connectors.DataCacheConnector
import controllers.actions._
import config.FrontendAppConfig
import forms.ClaimantFormProvider
import identifiers.ClaimantId
import models.Claimant
import utils.{Enumerable, Navigator, UserAnswers}
import views.html.claimant

import scala.concurrent.Future

class ClaimantController @Inject()(
                                        appConfig: FrontendAppConfig,
                                        override val messagesApi: MessagesApi,
                                        dataCacheConnector: DataCacheConnector,
                                        navigator: Navigator,
                                        getData: DataRetrievalAction,
                                        formProvider: ClaimantFormProvider) extends FrontendController with I18nSupport with Enumerable.Implicits {

  val form = formProvider()

  def onPageLoad() = getData {
    implicit request =>
      val preparedForm = request.userAnswers.flatMap(_.claimant) match {
        case None => form
        case Some(value) => form.fill(value)
      }
      Ok(claimant(appConfig, preparedForm))
  }

  def onSubmit() = getData.async {
    implicit request =>
      form.bindFromRequest().fold(
        (formWithErrors: Form[_]) =>
          Future.successful(BadRequest(claimant(appConfig, formWithErrors))),
        (value) =>
          dataCacheConnector.save[Claimant](request.sessionId, ClaimantId, value).map(cacheMap =>
            Redirect(navigator.nextPage(ClaimantId)(new UserAnswers(cacheMap))))
      )
  }
}
