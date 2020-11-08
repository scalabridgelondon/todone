package todone.component

import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactElement

import todone.data.Tag

@react object TagList extends SelectionList {
  type Element = Tag
  def renderElement(element: Tag): ReactElement = element.name
  def toKey(element: Tag): String = element.name

  val title = "Tags"

  case class Props(projects: List[Tag])
  val component = FunctionalComponent[Props] { props =>
    render(props.projects)
  }
}
