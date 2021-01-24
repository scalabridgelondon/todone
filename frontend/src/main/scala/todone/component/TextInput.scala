package todone.component

import org.scalajs.dom.html.Input
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactRef
import slinky.web.html._
import todone.Style

@react object TextInput {

  case class Props(
      id: String,
      name: String,
      placeholder: String = "",
      ref: ReactRef[Input],
      style: Style,
      onEnter: Option[() => Unit] = None
  )
  val component = FunctionalComponent[Props] { props =>
    input(
      className := Styles.input.++(props.style).toString,
      id := props.id,
      name := props.name,
      placeholder := props.placeholder,
      ref := props.ref,
      `type` := "text",
      onKeyUp := (evt =>
        props.onEnter.foreach(f => if(evt.key == "Enter") f())
      )
    )
  }
}
