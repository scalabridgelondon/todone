package todone.component

import todone.Style

object Styles {
  val textDimmed = Style("text-gray-400")
  val textHoverAccent = Style("hover:text-blue-500")
  val borderDimmed = Style("border-gray-400")
  val borderHoverAccent = Style("hover:border-blue-500")
  val borderFocusAccent = Style("focus:border-blue-500")
  val border = Style("border-2", "rounded", "border-gray-200")
  val input = Style("focus:outline-none", "ring-2", "ring-gray-200", "focus:ring-blue-500", "hover:ring-blue-500", "rounded")
  val button = Style("focus:outline-none", "border-2", "border-blue-400", "rounded", "text-blue-400", "hover:text-blue-500", "hover:border-blue-500")
}
