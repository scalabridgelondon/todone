package todone.component

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

@react object Titlebar {
  val component = FunctionalComponent[Unit] { _ =>
    div(className := "bg-blue-200 text-gray-900")(
      div(className := "py-2 px-8")(
        h3(
          span(className := "font-bold text-2xl")(
            "To", span(className := "text-blue-700")("Done")
          ),
          span(className := "text-lg text-gray-600")(", a ScalaBridge Production")
        )
      )
    )
  }
}
