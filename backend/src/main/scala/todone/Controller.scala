package todone

import todone.data._

object Controller {
  def tasks: Tasks =
    Tasks(Model.tasks.toList)

  def close(id: Id): Option[Task] =
    Model.tasks.updateWith(id)(task => task.map(_.close))
}
