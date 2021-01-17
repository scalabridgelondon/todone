package todone.component

import slinky.core.facade.ReactElement
import slinky.web.html._

/**
  * Displays a list of objects, and allow one to be selected.
  *
  * onSelect is called when an element is selected
  * onClear is called
  */
abstract class SelectionList[Element](
    onSelect: Element => Unit,
    onClear: () => Unit
) {
  def renderElement(element: Element): ReactElement
  def toKey(element: Element): String

  def title: String

  def render(elements: List[Element]): ReactElement =
    div(className := "pb-4")(
      h3(className := "pb-2")(title),
      ul(
        elements.map(elt =>
          li(
            className := "text-gray-600",
            key := toKey(elt),
            onClick := (() => onSelect(elt))
          )(
            renderElement(elt)
          )
        )
      )
    )
}
