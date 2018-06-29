import static org.assertj.core.api.Assertions.assertThat

import groovy.transform.EqualsAndHashCode
import groovy.transform.MapConstructor
import groovy.transform.ToString
import org.assertj.core.api.iterable.Extractor
import spock.lang.Specification

class EmployeesAndPeopleComparisionSpec extends Specification {

    def 'can assert that a list of employees matches a list of people'() {
        when:
        def people = [new Person(name: "Marcin", age: 30), new Person(name: "Erik", age: 40)]
        def employees = [new Employee(id: 1, fullName: "Marcin", age: 30), new Employee(id: 2, fullName: "Erik", age: 40)]

        then:
        assertThat(people)
            .extracting("name", "age")
            .isEqualTo(employees.extract("fullName", "age"))

        and:
        assertThat(people)
            .extracting(personNameAndAge())
            .isEqualTo(employees.extract(employeeNameAndAge()))
    }

    Extractor<Person, NameAndAge> personNameAndAge() {
        { person -> new NameAndAge(name: person.name, age: person.age) }
    }

    Extractor<Employee, NameAndAge> employeeNameAndAge() {
        { employee -> new NameAndAge(name: employee.fullName, age: employee.age) }
    }

    @EqualsAndHashCode
    @ToString(includeNames = true)
    @MapConstructor
    private static class NameAndAge {
        final String name
        final int age
    }

}
