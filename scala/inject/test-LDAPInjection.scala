// License: LGPL-3.0 License (c) find-sec-bugs
package inject

import org.springframework.ldap.core.DefaultNameClassPairMapper
import org.springframework.ldap.core.DirContextProcessor
import org.springframework.ldap.core.LdapEntryIdentificationContextMapper
import org.springframework.ldap.core.LdapTemplate
import org.springframework.ldap.core.support.CountNameClassPairCallbackHandler
import org.springframework.ldap.core.support.DefaultIncrementalAttributesMapper
import javax.naming.Context
import javax.naming.NamingEnumeration
import javax.naming.NamingException
import javax.naming.directory.DirContext
import javax.naming.directory.InitialDirContext
import javax.naming.directory.SearchControls
import javax.naming.directory.SearchResult
import java.util
import java.util.Properties


object LDAPInjection {
  private val ldapURI = "ldaps://ldap.server.com/dc=ldap,dc=server,dc=com"
  private val contextFactory = "com.sun.jndi.ldap.LdapCtxFactory"

  /** *************** JNDI LDAP ********************* */
  private[inject] def authenticate(username: Nothing, password: Nothing) = try {
    val props = new Nothing
    props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory")
    props.put(Context.PROVIDER_URL, "ldap://ldap.example.com")
    props.put(Context.REFERRAL, "ignore")
    props.put(Context.SECURITY_PRINCIPAL, dnFromUser(username))
    props.put(Context.SECURITY_CREDENTIALS, password)
    new Nothing(props)
    true
  } catch {
    case e: Nothing =>
      false
  }

  @throws[NamingException]
  private def dnFromUser(username: Nothing) = {
    val props = new Nothing
    props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory")
    props.put(Context.PROVIDER_URL, "ldap://ldap.example.com")
    props.put(Context.REFERRAL, "ignore")
    val context = new Nothing(props)
    val ctrls = new Nothing
    ctrls.setReturningAttributes(Array[Nothing]("givenName", "sn"))
    ctrls.setSearchScope(SearchControls.SUBTREE_SCOPE)
    val answers = context.search("dc=People,dc=example,dc=com", "(uid=" + username + ")", ctrls)
    val result = answers.next
    result.getNameInNamespace
  }

  @throws[Exception]
  private def ldapContext(env: Nothing) = {
    env.put(Context.INITIAL_CONTEXT_FACTORY, contextFactory)
    env.put(Context.PROVIDER_URL, ldapURI)
    env.put(Context.SECURITY_AUTHENTICATION, "none")
    val ctx = new Nothing(env)
    ctx
  }

  @throws[Exception]
  def testBind(dn: Nothing, password: Nothing): Boolean = {
    val env = new Nothing
    env.put(Context.SECURITY_AUTHENTICATION, "simple") //false positive

    env.put(Context.SECURITY_PRINCIPAL, dn)
    env.put(Context.SECURITY_CREDENTIALS, password)
    try ldapContext(env)
    catch {
      case e: Nothing =>
        return false
    }
    true
  }

  /** *************** JNDI LDAP SPECIAL ********************* */
  @throws[NamingException]
  def main(param: Nothing): Unit = {
    val ctx = null
    val base = "ou=users,ou=system"
    val sc = new Nothing
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE)
    val filter = "(&(objectclass=person))(|(uid=" + param + ")(street={0}))"
    val filters = Array[Nothing]("The streetz 4 Ms bar")
    System.out.println("Filter " + filter)
    val results = ctx.search(base, filter, filters, sc)
  }
}

class LDAPInjection {
  /** *************** SPRING LDAP ********************* */
  @throws[NamingException]
  def queryVulnerableToInjection(template: Nothing, jndiInjectMe: Nothing, searchControls: Nothing, dirContextProcessor: Nothing): Unit = {
    template.list(jndiInjectMe)
    template.list(jndiInjectMe, new Nothing)
    template.list(jndiInjectMe, new Nothing)
    template.lookup(jndiInjectMe)
    val mapper = new Nothing("")
    template.lookup(jndiInjectMe, mapper)
    template.lookup(jndiInjectMe, new Nothing)
    template.search(jndiInjectMe, "dn=1", searchControls, new Nothing)
    template.search(jndiInjectMe, "dn=1", searchControls, mapper, dirContextProcessor)
    template.search(jndiInjectMe, "dn=1", searchControls, new Nothing, dirContextProcessor)
    template.search(jndiInjectMe, "dn=1", searchControls, new Nothing, dirContextProcessor)
    template.search(jndiInjectMe, "dn=1", SearchControls.OBJECT_SCOPE, true, new Nothing)
    template.search(jndiInjectMe, "dn=1", new Nothing)
    template.search(jndiInjectMe, "dn=1", SearchControls.OBJECT_SCOPE, new Array[Nothing](0), mapper)
    template.search(jndiInjectMe, "dn=1", SearchControls.OBJECT_SCOPE, mapper)
    template.search(jndiInjectMe, "dn=1", mapper)
    template.search(jndiInjectMe, "dn=1", SearchControls.OBJECT_SCOPE, new Array[Nothing](0), new Nothing)
    template.search(jndiInjectMe, "dn=1", SearchControls.OBJECT_SCOPE, new Nothing)
    template.search(jndiInjectMe, "dn=1", new Nothing)
    template.search(jndiInjectMe, "dn=1", searchControls, new Nothing)
    template.search(jndiInjectMe, "dn=1", searchControls, mapper)
  }

  @throws[NamingException]
  def safeQuery(template: Nothing, searchControls: Nothing, dirContextProcessor: Nothing): Unit = {
    val safeQuery = "uid=test"
    template.list(safeQuery)
    template.list(safeQuery, new Nothing)
    template.list(safeQuery, new Nothing)
    template.lookup(safeQuery)
    val mapper = new Nothing("")
    template.lookup(safeQuery, mapper)
    template.lookup(safeQuery, new Nothing)
    template.search(safeQuery, "dn=1", searchControls, new Nothing)
    template.search(safeQuery, "dn=1", searchControls, mapper, dirContextProcessor)
    template.search(safeQuery, "dn=1", searchControls, new Nothing, dirContextProcessor)
    template.search(safeQuery, "dn=1", searchControls, new Nothing, dirContextProcessor)
    template.search(safeQuery, "dn=1", SearchControls.OBJECT_SCOPE, true, new Nothing)
    template.search(safeQuery, "dn=1", new Nothing)
    template.search(safeQuery, "dn=1", SearchControls.OBJECT_SCOPE, new Array[Nothing](0), mapper)
    template.search(safeQuery, "dn=1", SearchControls.OBJECT_SCOPE, mapper)
    template.search(safeQuery, "dn=1", mapper)
    template.search(safeQuery, "dn=1", SearchControls.OBJECT_SCOPE, new Array[Nothing](0), new Nothing)
    template.search(safeQuery, "dn=1", SearchControls.OBJECT_SCOPE, new Nothing)
    template.search(safeQuery, "dn=1", new Nothing)
    template.search(safeQuery, "dn=1", searchControls, new Nothing)
    template.search(safeQuery, "dn=1", searchControls, mapper)
  }
}
