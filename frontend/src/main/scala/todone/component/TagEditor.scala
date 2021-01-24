package todone.component

import org.scalajs.dom.html._
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.core.facade.React
import slinky.web.html._
import todone.Style
import todone.data

@react object TagEditor {
  val component = FunctionalComponent[Style] { style =>
    val (tags, updateTags) = useState(List.empty[data.Tag])
    val (tagRef, _) = useState(React.createRef[Input])

    def addTag(tag: String): Unit =
      updateTags{ tags =>
        val t = data.Tag(tag)
        if(tag == "" || tags.contains(t)) tags else {
          tagRef.current.value = ""
          tags :+ t
        }
      }

    div(
      TagsView(tags),
      TextInput(
        id = "tag",
        name = "tag",
        placeholder = "Tag",
        ref = tagRef,
        style = style,
        onEnter = Option(() => addTag(tagRef.current.value))
      )
    )
  }
}