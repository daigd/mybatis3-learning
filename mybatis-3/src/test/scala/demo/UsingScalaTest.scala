package demo

import common.UnitSpec

/**
 * @author : DaiGD
 * @createtime :  2020年12月03日 14:36
 * @description : 
 */
class UsingScalaTest extends UnitSpec{
  // 在AnyFlatSpec中的每个测试由句子构成，该句子指定了一些所需行为和一个测试它的代码块
  // a. 该句子需要一个主题，如" A Stack"
  // b. 一个动词，如should, must, can
  // 示例： "A List" should "be empty on create"
  // c. 如果多个测试的主题相同，可以使用it来指代之前的主题，如：
  // it should "increase in size upon add"
  // d. 句子后面需要增加"in"，其后紧跟着以{}括起来的测试代码示例。
  trait EmptyArrayList {
    val list = new java.util.ArrayList[String]
  }

  "A List" should "be empty on create" in new EmptyArrayList {
    list.size shouldBe 0
  }

  it should "increase in size upon add" in new EmptyArrayList {
    list.add("Milk")
    list add "Sugar"

    list.size should be(2)
  }
}
