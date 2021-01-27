package todone.component

import org.scalajs.dom.html._
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.core.facade.React
import slinky.web.html._
import todone.Style
import todone.data

@react object TaskEditor {

  val inputStyle = Style(
    "w-full",
    "p-2",
    "mb-2"
  )

  case class Props(onCreate: data.Task => Unit)
  val component = FunctionalComponent[Props] { props =>
    val ((titleRef, projectRef, descriptionRef), _) =
      useState(
        (
          React.createRef[Input],
          React.createRef[Input],
          React.createRef[TextArea]
        )
      )

    val (tags, updateTags) = useState(data.Tags.empty)

    val onClick =
      () => {
        val title = titleRef.current.value
        val project = projectRef.current.value
        val description = descriptionRef.current.value
        val task = data.Task(
          state = data.State.open,
          title = title,
          description = description,
          project = if (project.isEmpty()) None else Some(data.Project(project)),
          tags = tags
        )

        props.onCreate(task)
      }

    div(className := "bg-white px-4 py-2")(
      h3(className := "pb-2")("New Task"),
      TextInput(
        id = "title",
        name = "title",
        placeholder = "Title",
        ref = titleRef,
        style = "text-lg font-bold" +: inputStyle
      ),
      TextAreaInput(
        id = "descrption",
        name = "description",
        placeholder = "Description",
        ref = descriptionRef,
        style = inputStyle
      ),
      TextInput(
        id = "project",
        name = "project",
        placeholder = "Project",
        ref = projectRef,
        style = "text-sm" +: inputStyle
      ),
      TagsEditor(tags = tags, updateTags = updateTags, style = inputStyle),
      Button(
        onClick = onClick,
        label = "Create new task",
        style = Style("p-2", "my-2")
      )
    )
  }
}
