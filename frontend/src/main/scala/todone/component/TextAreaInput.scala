package todone.component

import org.scalajs.dom.html.TextArea
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactRef
import slinky.web.html._
import todone.Style

@react object TextAreaInput {
  case class Props(id: String, name: String, placeholder: String = "", ref: ReactRef[TextArea], style: Style)
  val component = FunctionalComponent[Props] { props =>
    textarea(
      className := Styles.input.++(props.style).toString,
      id := props.id,
      name := props.name,
      placeholder := props.placeholder,
      ref := props.ref)
  }
}
