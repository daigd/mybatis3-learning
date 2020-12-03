package common
import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, GivenWhenThen, Inside, Inspectors, OptionValues}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalamock.scalatest.MockFactory

/**
 * @author : DaiGD
 * @createtime :  2020年09月08日 17:15
 * @description : 单元测试基类
 * AnyFlatSpec ： 指定测试用例 Trait,FlatSpec的结构类似于XUnit,
 * 但是测试名称必须写成规定样式，如X should Y, A must be等     
 *
 * 一般情况下，推荐使用AnyFlatSpec样式用于单元测试和集成测试，AnyFeatureSpec用于验收测试。
 *
 * 参考文章：https://www.cnblogs.com/mengrennwpu/p/11301277.html
 *
 */
abstract class UnitSpec extends AnyFlatSpec with GivenWhenThen with BeforeAndAfter with BeforeAndAfterAll
  with Matchers with OptionValues with Inside with Inspectors with MockFactory
{
}