package todone.component

import slinky.core._
import slinky.core.facade.ReactElement
import todone.data.{Project, Projects}

class ProjectList(onSelect: Project => Unit, onClear: () => Unit)
    extends SelectionList[Project](onSelect, onClear) {
  def renderElement(element: Project): ReactElement = element.name
  def toKey(element: Project): String = element.name

  val title = "Projects"

  case class Props(projects: Projects)
  def apply(projects: Projects) =
    component(Props(projects))

  val component = FunctionalComponent[Props] { props =>
    render(props.projects.projects)
  }
}
