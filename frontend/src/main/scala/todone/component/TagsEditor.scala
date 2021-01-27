package todone.component

import org.scalajs.dom.html._
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.core.facade.{React, SetStateHookCallback}
import slinky.web.html._
import todone.Style
import todone.data

@react object TagsEditor {
  case class Props(
      tags: data.Tags,
      updateTags: SetStateHookCallback[data.Tags],
      style: Style
  )

  val component = FunctionalComponent[Props] { props =>
    val (tagRef, _) = useState(React.createRef[Input])

    def addTag(tag: String): Unit =
      props.updateTags { (tags: data.Tags) =>
        val t = data.Tag(tag)
        if (tag == "" || tags.contains(t)) tags
        else {
          tagRef.current.value = ""
          tags.append(t)
        }
      }

    div(
      TagsView(
        tags = props.tags,
        removeTag = (tag => props.updateTags(tags => tags.remove(tag)))
      ),
      TextInput(
        id = "tag",
        name = "tag",
        placeholder = "Tag",
        ref = tagRef,
        style = props.style,
        onEnter = Option(() => addTag(tagRef.current.value))
      )
    )
  }
}
