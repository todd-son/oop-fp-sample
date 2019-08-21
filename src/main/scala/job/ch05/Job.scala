package job.ch05

import job.ch05.Status._

// 작업은 사실 step이 실패하면 종료되어야 함

// harmful step의 상태에 직접 접근했다. val results = steps.takeWhile(step => step.execute().status == Completed)
// 응집도 : 변경 주기가 같은 객체들은 같이 모아라. 응집도가 높다. 변경의 주기가 같지 않은 객체들을 참조하고 있다 응집도가 낮다.

//
case class Job(steps: List[Step]) {
  def execute(): Job = {
    val results = steps.takeWhile(step => step.execute().status == Completed)

    this.copy(steps = results ++ steps.drop(results.size))
  }

  def status(jobStatusDecider: JobStatusDecider): Status = jobStatusDecider.decide(this)
}

class JobStatusDecider {
  def decide(job: Job): Status =  {
    val steps = job.steps

    if (steps.forall(step => step.status == Completed))
      Completed
    else if (steps.forall(step => step.status == Initialized))
      Initialized
    else if (steps.exists(step => step.status == Failed))
      Failed
    else
      Started
  }
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
  case object Initialized extends Status

  case object Started extends Status

  case object Completed extends Status

  case object Failed extends Status
}


