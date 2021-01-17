package todone.component

import scala.util._
import org.scalajs.dom.console
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.web.html._

import todone.api.Api
import todone.data._

@react object Controller {

  val component = FunctionalComponent[Unit] { _ =>
    object State {
      val (tasks, updateTasks) = useState(Tasks.empty)
      val (tags, updateTags) = useState(Tags.empty)
      val (projects, updateProjects) = useState(Projects.empty)

      val (tag, updateTag) = useState(Option.empty[todone.data.Tag])
      val (project, updateProject) = useState(Option.empty[Project])
    }

    object Effects {
      implicit val ec = Api.ec

      val close: Id => Unit = id => Api.close(id).onComplete(_ => data())

      val create: Task => Unit =
        task => Api.create(task).onComplete(_ => data())

      val tasks: () => Unit = () =>
        Api.tasks.onComplete {
          case Success(tasks) => State.updateTasks(tasks)
          case Failure(err)   => console.error(err.getMessage())
        }

      val tags: () => Unit = () =>
        Api.tags.onComplete {
          case Success(tags) => State.updateTags(tags)
          case Failure(err)  => console.error(err.getMessage())
        }

      val projects: () => Unit = () =>
        Api.projects.onComplete {
          case Success(projects) => State.updateProjects(projects)
          case Failure(err)  => console.error(err.getMessage())
        }

      val data: () => Unit = () => {
        tasks()
        tags()
        projects()
      }
    }

    val tagList = new TagList(
      onSelect = tag => State.updateTag(_ => Some(tag)),
      onClear = () => State.updateTag(_ => None)
    )

    val projectList = new ProjectList(
      onSelect = project => State.updateProject(_ => Some(project)),
      onClear = () => State.updateProject(_ => None)
    )

    // Initialize data from the server
    useEffect(Effects.data, List.empty)

    Container(
      left = div(
        projectList(State.projects),
        tagList(State.tags)
      ),
      right = div(
        TaskList(tasks = State.tasks, close = Effects.close),
        div(className := "p-4"),
        TaskEditor(Effects.create)
      )
    )
  }
}
