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

import controllers.actions._
import models.Claimant.You
import models.ClaimYears
import models.ClaimYears.FourYearsAgo
import play.api.test.Helpers._
import views.html.cannotClaimReliefTooLongAgo

class CannotClaimReliefTooLongAgoControllerSpec extends ControllerSpecBase {

  val claimant = You

  val fourYearsAgo = ClaimYears.getTaxYear(FourYearsAgo)
  val startYear = fourYearsAgo.startYear.toString

  def controller(dataRetrievalAction: DataRetrievalAction = getCacheMapWithClaimant(claimant)) =
    new CannotClaimReliefTooLongAgoController(frontendAppConfig, messagesApi, dataRetrievalAction, new DataRequiredActionImpl,
      new GetClaimantActionImpl)

  def viewAsString() = cannotClaimReliefTooLongAgo(frontendAppConfig, claimant, startYear)(fakeRequest, messages).toString

  "CannotClaimReliefTooLongAgo Controller" must {

    "return OK and the correct view for a GET" in {
      val result = controller().onPageLoad(fakeRequest)

      status(result) mustBe OK
      contentAsString(result) mustBe viewAsString()
    }
  }
}
