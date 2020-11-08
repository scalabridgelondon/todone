package todone.component

import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactElement

import todone.data.Project

@react object ProjectList extends SelectionList {
  type Element = Project
  def renderElement(element: Project): ReactElement = element.name
  def toKey(element: Project): String = element.name

  val title = "Projects"

  case class Props(projects: List[Project])
  val component = FunctionalComponent[Props] { props =>
    render(props.projects)
  }
}
