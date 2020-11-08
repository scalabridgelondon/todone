package todone.component

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._
import slinky.core.facade.ReactElement

@react object Container {
  case class Props(left: ReactElement, right: ReactElement)
  val component = FunctionalComponent[Props] { props =>
    div(className := "text-gray-900")(
      Titlebar(()),
      div(className := "flex h-screen")(
        div(className := "w-1/4 px-8 py-8")(props.left),
        div(className := "w-3/4 bg-gray-300 px-8 py-8")(props.right)
      )
    )
  }
}
