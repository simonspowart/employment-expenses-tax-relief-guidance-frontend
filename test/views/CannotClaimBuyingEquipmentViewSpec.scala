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

package views

import models.Claimant.You
import play.api.mvc.Call
import views.behaviours.ViewBehaviours
import views.html.cannotClaimBuyingEquipment

class CannotClaimBuyingEquipmentViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "cannotClaimBuyingEquipment.you"

  val changeOtherExpensesPage = Call("GET", "route1")
  val changeUniformsWorkClothingToolsPage = Call("GET", "route2")

  def createView = () => cannotClaimBuyingEquipment(frontendAppConfig, You, changeOtherExpensesPage, changeUniformsWorkClothingToolsPage)(fakeRequest, messages)

  "CannotClaimBuyingEquipment view" must {
    behave like normalPage(createView, messageKeyPrefix, "guidance", "guidance2")
    behave like pageWithBackLink(createView)
  }
}
