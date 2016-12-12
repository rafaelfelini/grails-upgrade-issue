package ola31

import org.hibernate.FlushMode
import org.hibernate.Session
import org.springframework.orm.hibernate4.SessionHolder
import org.springframework.transaction.support.TransactionSynchronizationManager

class PersonController {

  def hibernateDatastore

  def sessionFactory

    def index() {
        def p = new Person(name:'rafael', age: 10)
        p.save(flush: true)

        render p
    }

    def show() {
      SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory)
      Session session = sessionHolder != null ? sessionHolder.getSession() : null

      println session.getFlushMode()
      println TransactionSynchronizationManager.isActualTransactionActive()

      def p = Person.findById(1)
      p.age = null
      p.addToPhones(new Phone())

      render p
    }

}
