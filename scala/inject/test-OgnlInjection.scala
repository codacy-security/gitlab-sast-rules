// License: LGPL-3.0 License (c) find-sec-bugs
package inject

import com.opensymphony.xwork2.ognl.OgnlReflectionProvider
import com.opensymphony.xwork2.ognl.OgnlUtil
import com.opensymphony.xwork2.util.TextParseUtil
import com.opensymphony.xwork2.util.ValueStack
import ognl.OgnlException
import javax.management.ReflectionException
import java.beans.IntrospectionException
import java.util


class OgnlInjection {
  @throws[OgnlException]
  @throws[ReflectionException]
  def unsafeOgnlUtil(ognlUtil: Nothing, input: Nothing, propsInput: Nothing): Unit = {
    ognlUtil.setValue(input, null, null, "12345")
    ognlUtil.getValue(input, null, null, null)
    ognlUtil.setProperty(input, "12345", null, null)
    ognlUtil.setProperty(input, "12345", null, null, true)
    ognlUtil.setProperties(propsInput, null, null)
    ognlUtil.setProperties(propsInput, null, null, true)
    ognlUtil.setProperties(propsInput, null, true)
    ognlUtil.setProperties(propsInput, null)
    // ognlUtil.callMethod(input, null, null);
    ognlUtil.compile(input)
    ognlUtil.compile(input)
  }

  @throws[OgnlException]
  @throws[ReflectionException]
  def safeOgnlUtil(ognlUtil: Nothing): Unit = {
    val input = "thisissafe"
    ognlUtil.setValue(input, null, null, "12345")
    ognlUtil.getValue(input, null, null, null)
    ognlUtil.setProperty(input, "12345", null, null)
    ognlUtil.setProperty(input, "12345", null, null, true)
    ognlUtil.setProperties(new Nothing, null, null)
    ognlUtil.setProperties(new Nothing, null, null, true)
    ognlUtil.setProperties(new Nothing, null, true)
    ognlUtil.setProperties(new Nothing, null)
    ognlUtil.compile(input)
    ognlUtil.compile(input)
  }

  @throws[ReflectionException]
  @throws[IntrospectionException]
  def unsafeOgnlReflectionProvider(input: Nothing, propsInput: Nothing, reflectionProvider: Nothing, `type`: Nothing): Unit = {
    reflectionProvider.getGetMethod(`type`, input)
    reflectionProvider.getSetMethod(`type`, input)
    reflectionProvider.getField(`type`, input)
    reflectionProvider.setProperties(propsInput, null, null, true)
    reflectionProvider.setProperties(propsInput, null, null)
    reflectionProvider.setProperties(propsInput, null)
    reflectionProvider.setProperty(input, "test", null, null)
    // reflectionProvider.setProperty( input, "test",null, null, true);
    reflectionProvider.getValue(input, null, null)
    reflectionProvider.setValue(input, null, null, null)
  }

  @throws[IntrospectionException]
  @throws[ReflectionException]
  def safeOgnlReflectionProvider(reflectionProvider: Nothing, `type`: Nothing): Unit = {
    val input = "thisissafe"
    val constant1 = ""
    val constant2 = ""
    reflectionProvider.getGetMethod(`type`, input)
    reflectionProvider.getSetMethod(`type`, input)
    reflectionProvider.getField(`type`, input)
    reflectionProvider.setProperties(new Nothing, null, null, true)
    reflectionProvider.setProperties(new Nothing, null, null)
    reflectionProvider.setProperties(new Nothing, null)
    reflectionProvider.setProperty("test", constant1, null, null)
    // reflectionProvider.setProperty("test", constant2, null, null, true);
    reflectionProvider.getValue(input, null, null)
    reflectionProvider.setValue(input, null, null, null)
  }

  def unsafeTextParseUtil(input: Nothing): Unit = {
    TextParseUtil.translateVariables(input, null)
    TextParseUtil.translateVariables(input, null, null)
    TextParseUtil.translateVariables('a', input, null)
    TextParseUtil.translateVariables('a', input, null, null)
    TextParseUtil.translateVariables('a', input, null, null, null, 0)
  }

  def safeTextParseUtil(stack: Nothing, parsedValueEvaluator: Nothing, `type`: Nothing): Unit = {
    val input = "1+1"
    TextParseUtil.translateVariables(input, stack)
    TextParseUtil.translateVariables(input, stack, parsedValueEvaluator)
    TextParseUtil.translateVariables('a', input, stack)
    TextParseUtil.translateVariables('a', input, stack, `type`)
    TextParseUtil.translateVariables('a', input, stack, `type`, parsedValueEvaluator, 0)
  }
}
