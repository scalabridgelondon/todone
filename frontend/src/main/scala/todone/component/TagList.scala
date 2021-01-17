package todone.component

import slinky.core._
import slinky.core.facade.ReactElement
import todone.data.{Tag, Tags}

class TagList(onSelect: Tag => Unit, onClear: () => Unit)
    extends SelectionList[Tag](onSelect, onClear) {
  def renderElement(element: Tag): ReactElement = element.name
  def toKey(element: Tag): String = element.name

  val title = "Tags"

  case class Props(tags: Tags)
  def apply(tags: Tags) =
    component(Props(tags))

  val component = FunctionalComponent[Props] { props =>
    render(props.tags.tags)
  }
}
