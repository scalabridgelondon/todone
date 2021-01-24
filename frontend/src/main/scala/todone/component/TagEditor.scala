package todone.component

import org.scalajs.dom.html._
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.core.facade.React
import slinky.web.html._
import todone.data

@react object TagEditor {
  val component = FunctionalComponent[Unit] { _ =>
    val (tags, updateTags) = useState(List.empty[data.Tag])
    val (tagRef, _) = useState(React.createRef[Input])

    div(
      ul(tags.map(t => li(t.name))),
      TextInput(
        id = "tag",
        name = "tag",
        placeholder = "Tag",
        ref = tagRef,
        style = Styles.input
      )
    )
  }
}
