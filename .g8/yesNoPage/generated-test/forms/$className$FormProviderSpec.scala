package forms

import forms.behaviours.BooleanFieldBehaviours
import play.api.data.FormError

class $className$FormProviderSpec extends BooleanFieldBehaviours {

  val requiredKey = "$className;format="decap"$.error.required"

  val form = new $className$FormProvider()()

  ".value" must {

    val fieldName = "value"

    behave like booleanField(
      form,
      fieldName,
      invalidError = FormError(fieldName, requiredKey)
    )

    behave like mandatoryField(
      form,
      fieldName,
      requiredError = FormError(fieldName, requiredKey)
    )
  }
}
