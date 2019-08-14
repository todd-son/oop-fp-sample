package job.ch02

case class Job(steps: List[Step]) {
  def execute(): Job = {
    val results = steps.map(step => step.execute())

    this.copy(steps = results)
  }

  def status: Status = ???
}

sealed trait Step {
  def execute(): Step

  val status: Status

}

case class LoadStep(status: Status) extends Step {
  override def execute(): Step = ???
}

case class MappingStep(status: Status) extends Step {
  override def execute(): Step = ???
}



sealed trait Status

object Status {
  case object Started extends Status

  case object Completed extends Status

  case object Failed extends Status
}


