package todone.component

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._
import todone.Style

@react object Button {
  case class Props(label: String, onClick: () => Unit, style: Style)
  val component = FunctionalComponent[Props] { props =>
    button(
      className := Styles.button.++(props.style).toString,
      onClick := props.onClick
    )(props.label)
  }
}
