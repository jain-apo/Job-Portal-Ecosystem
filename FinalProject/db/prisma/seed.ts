import { Course, Person, Prisma, PrismaClient, Role } from '@prisma/client'

const prisma = new PrismaClient()

async function main() {


    const admin = await addRole({ name: "ADMIN" })

    // add the following roles: Company HR, Company Sysadmin, Team Lead, Team Manager, Job Portal User, Job Portal Admin, College Student, College HR, Trainee, Trainer
    // Company Roles
    const companyHR = await addRole({ name: "COMPANY_HR" })
    const companySysadmin = await addRole({ name: "COMPANY_SYSADMIN" })
    const companyEmployee = await addRole({ name: "COMPANY_EMPLOYEE" })

    // Job Portal Roles
    const jobPortalUser = await addRole({ name: "JOB_PORTAL_USER" })
    const jobPortalAdmin = await addRole({ name: "JOB_PORTAL_ADMIN" })

    // College Roles
    const collegeStudent = await addRole({ name: "COLLEGE_STUDENT" })
    const collegeHR = await addRole({ name: "COLLEGE_HR" })
    const collegeAdmin = await addRole({ name: "COLLEGE_ADMIN" })

    // Training Site Roles
    const trainee = await addRole({ name: "TRAINEE" })
    const trainer = await addRole({ name: "TRAINING_SITE_ADMIN" })

    const sharun = await addUser({
        firstName: 'Sharun',
        lastName: 'Kumar',
        username: 'sharun',
        phone: '9043133610',
        password: '$2a$12$NReSOnyEHTMjnmYwxLa7be49S4aIsSXi8ROHIeyg/3ZXFwEnmfEee',
        dateOfBirth: new Date('Sun Nov 05 1995 00:04:24 GMT-0500 (Eastern Standard Time)'),
        email: "sharunksplus@gmail.com",
    })

    const apoorva = await addUser({
        firstName: 'Apoorva',
        lastName: 'Jain',
        username: 'apoorva',
        email: "jain.apo@gmail.com",
        phone: '9043133610',
        password: '$2a$12$NReSOnyEHTMjnmYwxLa7be49S4aIsSXi8ROHIeyg/3ZXFwEnmfEee',
        dateOfBirth: new Date('Sun Nov 05 1995 00:04:24 GMT-0500 (Eastern Standard Time)'),
    })

    const mihir = await addUser({
        firstName: 'Mihir',
        lastName: 'Sheth',
        username: 'mihir',
        email: "mihir@gmail.com",
        phone: '9043133610',
        password: '$2a$12$NReSOnyEHTMjnmYwxLa7be49S4aIsSXi8ROHIeyg/3ZXFwEnmfEee',
        dateOfBirth: new Date('Sun Nov 05 1995 00:04:24 GMT-0500 (Eastern Standard Time)'),
    })

    await addPersonRole(sharun, admin)
    await addPersonRole(sharun, collegeAdmin)
    await addPersonRole(sharun, collegeHR)
    await addPersonRole(sharun, companyHR)
    await addPersonRole(sharun, trainer)

    const courses = await addCourses(["Software Developent", "Data Science", "Finance"])

    await prisma.collegeStudent.create({
        data: {
            personId: apoorva.id,
            gpa: 3.5,
            passYear: 2023,
            courseId: courses[0].id
        }
    })

    await addPersonRole(apoorva, collegeStudent)
    await addPersonRole(apoorva, trainee)


    await prisma.personNotification.upsert({
        where: {
            id: 1
        },
        update: {
            title: "Welcome",
            message: "Welcome to the dashboard, " + sharun.firstName,
        },
        create: {
            personId: sharun.id,
            title: "Welcome",
            message: "Welcome to the dashboard, " + sharun.firstName,
        }
    })

    console.log({ sharun })

    // insert a company to the database
    const google = await addCompany("Google")
    const microsoft = await addCompany("Microsoft")
    const amazon = await addCompany("Amazon")
    const facebook = await addCompany("Facebook")
    const apple = await addCompany("Apple")
    const netflix = await addCompany("Netflix")

    // insert a JobPosting
    const jobPosting = await addJobPosting(google)

    // insert a JobApplication
    const jobApplication = await addJobApplication(sharun, jobPosting)

    // insert a JobCandidate
    const jobCandidate = await addJobCandidate(sharun, jobApplication)

    // const trainingModules = ["JavaScript", "C++", "C#"]

    // const modules = []

    // for (let index = 0; index < trainingModules.length; index++) {
    //     const module = trainingModules[index];
    //     modules.push(await addTrainingModule(module))
    // }

    // // console.log({ modules })

    // for (let index = 0; index < modules.length; index++) {
    //     const module = modules[index];

    //     const chapters = ["Basics", "Advanced", "Expert"]

    //     for (let ch = 0; ch < chapters.length; ch++) {
    //         const chapter = chapters[ch];
    //         await prisma.moduleData.create({
    //             data: {
    //                 title: module.name + ` ${chapter}`,
    //                 description: module.name + ` ${chapter} Content`,
    //                 trainingModuleId: module.id
    //             }
    //         })
    //     }
    // }

    await addTrainingData()


}

main()
    .then(async () => {
        await prisma.$disconnect()
    })
    .catch(async (e) => {
        console.error(e)
        await prisma.$disconnect()
        process.exit(1)
    })

async function addTrainingData() {
    const javaScript = await addTrainingModule("JavaScript")

    await prisma.trainingModuleData.create({
        data: {
            title: "Basics",
            description: `
            JavaScript is an interpreted language. In the browser, an interpreter will read the code and run it without needing to compile it first.
Java is used as a server side (backend) language whereas JavaScript is primarily used as a client side (frontend) language. But JavaScript can also be used to create backend web applications with Node.js.`,
            trainingModuleId: javaScript.id
        }
    })

    await prisma.trainingModuleData.create({
        data: {
            title: "Intermediate",
            description: `
            The Document Object Model is a way to manipulate the structure and style of an HTML page. It represents the internals of the page as the browser sees it, and allows the developer to alter it with JavaScript.
Trees and Branches
HTML is an XML-like structure in that the elements form a structure of parents’ nodes with children, like the branches of a tree. There is one root element (html) with branches like head and body, each with their own branches. For this reason, the DOM is also called the DOM tree.

Modifying the DOM, by picking an element and changing something about it, is something done often in JavaScript. To access the DOM from JavaScript, the document object is used. It’s provided by the browser and allows code on the page to interact with the content.
Getting an Element
The first thing to know is how to get an element. There are a number of ways of doing it, and browsers support different ones. Starting with the best supported we’ll move through to the latest, and most useful, versions.
By ID
document.getElementById is a method for getting hold of an element - unsurprisingly - by its ID.


var pageHeader = document.getElementById('page-header');
The pageHeader element can then be manipulated - its size and color can be changed, and other code can be declared to handle the element being clicked on or hovered over. It’s supported in pretty much all the browsers you need to worry about.

Notice that getElementById is a method of the document object. Many of the methods used to access the page are found on the document object.

By Tag Name
document.getElementsByTagName works in much the same way as getElementById, except that it takes a tag name (a, ul, li, etc) instead of an ID and returns a NodeList, which is essentially an array of the DOM Elements.

By Class Name
document.getElementsByClassName returns the same kind of NodeList as getElementsByTagName, except that you pass a class name to be matched, not a tag name.

By CSS Selector
A couple of new methods are available in modern browsers that make selecting elements easier by allowing the use of CSS selectors. They are document.querySelector and document.querySelectorAll.


var pageHeader = document.querySelector('#header');
var buttons = document.querySelectorAll(.btn);
querySelector, like getElementById, returns only one element whereas querySelectorAll returns a NodeList. If multiple elements match the selector you pass to querySelector, only the first will be returned.
            `,
            trainingModuleId: javaScript.id
        }
    })

    await prisma.trainingModuleData.create({
        data: {
            title: "Advanced",
            description: `Closures to Extend Variable Scope
Closures in JavaScript are a fairly straightforward concept, and have been discussed online in a number of in-depth articles. The fact that they are straightforward doesn’t necessarily mean they’re simple however, as seen by the extensive articles that cover the subject. Simply put, closures allow variable scope to be extended past the common scope restrictions of functions.
To create a closure, you nest a function inside of a function. That inner function has access to all variables in its parent function’s scope. This comes in handy when creating methods and properties in object oriented scripts.

Here is a simple example that demonstrates the use of a closure:

function myObject() { this.property1 = "value1"; this.property2 = "value2"; var newValue = this.property1;  this.performMethod = function() { myMethodValue = newValue; return myMethodValue; }; } var myObjectInstance = new myObject(); alert(myObjectInstance.performMethod());
The key portions of the script are the nested anonymous function are highlighted in green and the method call in the alert function (last line). Because the method in the alert is actually calling a nested function, that method is able to read the value of the variable called newValue, even thought that variable is not within the scope of the anonymous function, or method. Developers use closures all the time, probably unknowingly, since a closure is created any time an anonymous function is nested inside another function and utilizes variables from the parent function’s scope.
`,
            trainingModuleId: javaScript.id
        }
    })

    await prisma.trainingQuestion.create({
        data: {
            question: `The statements inside an if statement are contained by the same curly braces used to contain the statements in a function.`,
            answer: false,
            trainingModuleId: javaScript.id
        }
    })

    await prisma.trainingQuestion.create({
        data: {
            question: `In an infinite loop, a loop statement never ends because its conditional expression is never truthy.`,
            answer: false,
            trainingModuleId: javaScript.id
        }
    })

    await prisma.trainingQuestion.create({
        data: {
            question: `The statement document.write("This is a text string."); prints "This is a text string"`,
            answer: false,
            trainingModuleId: javaScript.id
        }
    })

    const cPlusPLus = await addTrainingModule("C++")
    await prisma.trainingModuleData.create({
        data: {
            title: "Basics",
            description: `
        C++ is one of the world's most popular programming languages.
        C++ can be found in today's operating systems, Graphical User Interfaces, and embedded systems.
        C++ is an object-oriented programming language which gives a clear structure to programs and allows code to be reused, lowering development costs.
        C++ is portable and can be used to develop applications that can be adapted to multiple platforms.
        C++ is fun and easy to learn!
        As C++ is close to C, C# and Java, it makes it easy for programmers to switch to C++ or vice versa.`,
            trainingModuleId: cPlusPLus.id
        }
    })
    await prisma.trainingModuleData.create({
        data: {
            title: "Intermediate",
            description: `
            C++ is an object-oriented language. This paradigm is one of its defining differences from C and C#. In C++ we accomplish this by using objects and classes to store and manipulate data. Through this object-oriented approach, developers can implement the strategies of inheritance, polymorphism, abstraction, and encapsulation.
            Using Inheritance in C++
            Setting up inheritance relationships in C++ is easy; we simply append a classes declaration with : [parent name](). This allows us to share both public variables and methods from parent class to child.
            Using Polymorphism in C++
            One of the most common applications of polymorphism in C++ is through function overriding. By having two functions of the same name in different classes, we can create code that completes different behavior depending on the inheritance of the selected object.
            Abstraction and Encapsulation
            Abstraction works similarly in C++ as other hard-coded languages through the use of the private and public keywords. This is similar to abstraction, as abstraction acts to hide non-essential information. Encapsulation can be used to achieve abstraction via private getter and setter functions.`,
            trainingModuleId: cPlusPLus.id
        }
    })
    await prisma.trainingModuleData.create({
        data: {
            title: "Advance",
            description: `
            How do Strings work in C++?
            Strings in C++ are similar to those of other languages, in that they’re a collection of ordered characters. However, in C++, there are two ways to create strings, either using C style strings or the C++ string class.

            C style strings are the old-fashioned way of creating strings in C++. Rather than a standard string object, C-style strings consist of a null terminated array of characters ending with the special character \0. Due to this hidden character, C-style strings are always a length of one more character than the visible number of characters.

            This size can either be unspecified to be set automatically to the required size or manually to any desired size.
            We can also create strings in C++ using the C++ string class that is built into the standard C++ library. This is the more popular method, as all memory management, allocation, and null termination are internally handled by the class. Another advantage is that the length of the string can be changed at runtime thanks to the dynamic allocation of memory.

            Overall, these changes make the string class more resistant to errors and provide many built-in functions like append(), which adds onto the end of the string and length(), which returns the number of characters in the string.

            Below, we’ll learn how to use functions like these to complete common manipulations of strings.

            How to print a string
            We can print strings in C++ using the cout global object along with the << operator which precedes the printed content. We also include the endl global object which is used to skip a line after the operation for better readability. As both endl and cout are predefined objects of the global class ostream, we must make sure to include the <iostream> header in programs where they are needed.

            How to calculate the length of string
            To calculate the length of a string we can use either the length() or size() functions. Each performs identically and each exists to aid in readability. These functions can also be used to measure the length of STL containers like maps and vectors.
            The synonymous functions are included to increase readability as while length is intuitive to use for string, it would be more intuitive to refer to an array’s size than its length.

            What is a Pointer in C++?
            In C++, all variables must be stored somewhere within the host computer’s memory. To help programs find these variables without searching the memory, C++ allows us to use the special variable, pointers, to explicitly give the variable’s address.

            Using C++ Maps
            Maps are a type of container that store elements using key-value pairs. Each element on a map has a unique key as an identifier and a value that is retrieved when the key is called. Keys are automatically sorted smallest to largest, which makes searching maps very quick.
            How to sort a map by value in C++
            As stated above, maps sort by key automatically. However, it may be beneficial to sort by value instead. We can do this by copying the elements to a vector of key-value pairs then sorting the vector before finally printing.

            Tracking allocations and deallocations
            Up until now, our examples has one new and one delete, whereas practical programs may have dozens. When at a larger scale, it’s harder to know if you’ve deleted all unneeded pointers or to find those unused pointers. To do a simple check, we can simply count the number of new allocations and compare that to the number of delete deallocations.`,
            trainingModuleId: cPlusPLus.id
        }
    })
    await prisma.trainingQuestion.create({
        data: {
            question: `It is not possible to achieve inheritance of structures in c++?`,
            answer: false,
            trainingModuleId: cPlusPLus.id
        }
    })
    await prisma.trainingQuestion.create({
        data: {
            question: `Super classes are also called Parent classes/Base classes?`,
            answer: true,
            trainingModuleId: cPlusPLus.id
        }
    })
    await prisma.trainingQuestion.create({
        data: {
            question: `Sub classes may also be called Child classes/Derived classes?`,
            answer: true,
            trainingModuleId: cPlusPLus.id
        }
    })
    const csharp = await addTrainingModule("C#")
    await prisma.trainingModuleData.create({
        data: {
            title: "Basics",
            description: `
            C# is a simple & powerful object-oriented programming language developed by Microsoft. C# can be used to create various types of applications, such as web, windows, console applications, or other types of applications using Visual studio.`,

            trainingModuleId: csharp.id
        }
    })
    await prisma.trainingModuleData.create({
        data: {
            title: "Intermediate",
            description: `
                Constructors
                When we use the new keyword to create an object, the CLR (Common Language Runtime) uses the class definition to construct that object for us by calling a constructor method.
                The constructor is a special method that has the same name as the class it is defined in, doesn’t return any value (not even void) and can take parameters. It runs automatically when we create an instance of a class. So, every time we use the new keyword to instantiate a class, we are calling a constructor of that class.
                Every class must have a constructor. If we don’t write one, the compiler automatically generates one for us. This type of constructor is called a default constructor. A default constructor will set all the data inside a class, to their default values (assigned values if we don’t assign them). So, in our example, the fields _name and _lastName will have an empty string as a value at a beginning.`,
            trainingModuleId: csharp.id
        }
    })
    await prisma.trainingModuleData.create({
        data: {
            title: "Advance",
            description: `
                    Data Types and Operators
                    Before we can actually start doing something, we need to introduce the basic set of datatypes and operators. As already mentioned, C# is a static strongly-typed language, which is why we need to care about data types. In the end, we will have to specify what's the type behind any variable.

                    There is a set of elementary data types, which contains the following types:

                    bool (1 byte) used for logical expressions (true, false)
                    char (2 bytes) used for a single (unicode) character
                    short (2 bytes) used for storing short integers
                    int (4 bytes) used for computations with integers
                    long (8 bytes) used for computations with long integers
                    float (4 bytes) used for computations with single precision floating point numbers
                    double (8 bytes) used for computations with double precision floating point numbers
                    decimal (16 bytes) used for computations with fixed precision floating point numbers
                    There are also modified data types like unsigned integer (uint), unsigned long (ulong) and many more available. Additionally, a really well working class for using strings has been delivered. The class is just called string and works as one would expect, except that a lot of useful helpers are directly available.

                    Types alone are quite boring, since we can only instantiate them, i.e., create objects based on their definition. It gets more interesting once we connect them by using operators. In C#, we have the same set of operators (and more) as in C:

                    Logical operators like ==, !=, <, <=, >, >=, !, &&, ||
                    Bit operators like ^, &, |, <<, >>
                    Arithmetic operators like +, -, *, /, %, ++, --
                    The assignment operator = and combinations of the assignment operator with the binary operators
                    The ternary operator ? : (inline condition) to return either the left or right side of the colon depending on a condition specified before the question mark
                    Brackets () to change the operator hierarchy
                    Additionally, C# has some inbuilt-methods (defined as unary operators) like typeof or sizeof and a set of really useful type operators:

The standard cast operator () as in C
The reference cast operator as
The type conformance checker is
The null-coalescing operator ??
The inheritance operator :
Reference and Value Types
A very important concept for understanding C# is the difference between reference and value types. Once we use a class, we are using a reference type. On the other side, any object that is a struct will be handled as a value type. The important difference is that reference types will be passed by reference, i.e., we will only pass a copy of the position of the object, and not a copy of the object itself. Any modification on the object will result in a modification on the original object.

The other case is the one with value types. If we give a method an argument that is a struct (that includes integers, floating point numbers, a boolean, a character and more), we will get a copy of the passed object. This means that modifications on the argument will never result in a modification on the original object.

The whole concept is quite similar to the concept of pointers in C. The only difference is that we do not actually have access to the address and we do not have to dereference the variable to get access to the values behind it. One could say that C# handles some of the things automatically that we would have to write by hand in C/C++.

In C#, we have access to two keywords that can be passed together with the argument definition. One important keyword is called ref. This allows us to access the original value in the case of passing in structures. For classes, the consequence is that the original pointer is actually passed in, allowing us to change the position of it. This sounds quite strange at first, but we will see that in most cases there is no difference between an explicit ref on a class instance to an implicit by just passing the class. The only difference is that we can actually reset the pointer
Control Flow
Now that we introduced the basic concepts behind C#, as well as elementary data types and available operators, we just need one more thing before we can actually go ahead and write actual C# programs. We need to know how to control the program flow, i.e., how to introduce conditions and loops.

This is pretty much the same as in C, as we are dealing with a C-style syntax. That being said, we have to follow these rules:

Conditions can be introduced by using if or switch.
A loop is possible by using for, while, do-while.
A loop will be stopped by using the break keyword (will stop the most inner loop).
A loop can skip the rest and return to the condition with the continue keyword.
C# also has an iterator loop called foreach.
Another possibility is the infamous goto statement - we will not discuss this.
There are other ways of controlling the program flow, but we will introduce those later.
Inheritance and Polymorphism
Now we are coming to the inheritance issue. To simplify things, we can think of inheritance as a recursive copy paste process by the compiler. All members of the parent (base) class will be copied.

C#
class MySubClass : MyClass
{
    public void WriteMore()
    {
        Console.WriteLine("Hi again!");
    }
}
As already mentioned, the inheritance operator is the :. In this example, we create a new type called MySubClass, which inherits from MyClass. MyClass has been defined in the previous section and does not define an explicit inheritance. Therefore MyClass inherits from Object. Object itself just defines four methods, that are:

ToString, which is a very comfortable way of defining how an instance of the type should presented as a string
Equals, which is a generic way of comparing two arbitrary objects of equality
GetHashCode, which gets a numeric indicator if two objects could be equal
GetType, which gets the meta-information on the specific type of the current instance
These four methods are available at MyClass and MySubClass instances (copy paste!). Additionally, MyClass defines a method called Write, which will also be available for all MySubClass instances. Finally, MySubClass defines a method called WriteMore, which will only be available for MySubClass instances.

Right now the inheritance concept is already a little bit useful, but it is not very powerful. The concept of polymorphism will enable us to specialize objects using inheritance. First we will introduce the virtual keyword. This keyword lets us specify that a (virtual marked) method can be re-implemented by more specialized (or derived) classes.`,
            trainingModuleId: csharp.id
        }
    })
    await prisma.trainingQuestion.create({
        data: {
            question: `Arithmetic operators are called operators when you use two arguments with each operator. → binary
                    `,
            answer: true,
            trainingModuleId: csharp.id
        }
    })
    await prisma.trainingQuestion.create({
        data: {
            question: `The method returns a string that holds the name of the class, just as GetType() does. → WriteLine()`,
            answer: false,
            trainingModuleId: csharp.id
        }
    })
    await prisma.trainingQuestion.create({
        data: {
            question: `In C#, you can use either new or when defining a derived class member that has the same name as a base class member. → out
                    answer `,
            answer: false,
            trainingModuleId: csharp.id
        }
    })


}

async function addTrainingModule(name: string) {
    return await prisma.trainingModule.create({
        data: {
            name: name,
            description: name + " is a programming language"
        }
    })
}

async function addJobCandidate(sharun: Person, jobApplication: { id: any; personId?: number; jobPostingId?: number; yearsOfExperience?: number }) {
    return await prisma.jobCandidate.upsert({
        where: {
            personId_jobApplicationId: {
                personId: sharun.id,
                jobApplicationId: jobApplication.id
            }
        },
        create: {
            personId: sharun.id,
            jobApplicationId: jobApplication.id,
            interviewRound: 1
        },
        update: {
            personId: sharun.id,
            jobApplicationId: jobApplication.id
        }
    })
}

async function addJobApplication(sharun: Person, jobPosting: { id: any; title?: string; jobDescription?: string; category?: string; companyId?: number }) {
    return await prisma.jobApplication.upsert({
        where: {
            personId_jobPostingId: {
                personId: sharun.id,
                jobPostingId: jobPosting.id
            }
        },
        create: {
            personId: sharun.id,
            jobPostingId: jobPosting.id,
            yearsOfExperience: 5,
            resumeFile: ""
        },
        update: {
            personId: sharun.id,
            jobPostingId: jobPosting.id,
            yearsOfExperience: 5
        }
    })
}

async function addJobPosting(company: { id: any; name?: string }) {
    const jobPostingData = {
        title: "Software Engineer",
        jobDescription: "Software Engineer",
        category: "SDE",
        companyId: company.id
    }

    return await prisma.jobPosting.upsert({
        where: {
            title_companyId: {
                title: jobPostingData.title,
                companyId: company.id
            }
        },
        update: jobPostingData,
        create: jobPostingData
    })
}

async function addCompany(name: string) {
    return await prisma.company.upsert({
        where: {
            name: name
        },
        create: {
            name: name
        },
        update: {
            name: name
        }
    })
}

async function addRole(role: Prisma.RoleCreateInput) {
    return await prisma.role.upsert({
        where: {
            name: role.name
        },
        update: role,
        create: role,
    })
}

async function addUser(user: Prisma.PersonCreateInput) {
    return await prisma.person.create({
        data: {
            ...user,
        }
    })
}

async function addPersonRole(person: Person, role: Role) {

    const personRole: Prisma.PersonRoleCreateInput = {
        person: { connect: { id: person.id } },
        role: { connect: { id: role.id } }
    }

    return await prisma.personRole.upsert({
        where: {
            personId_roleId: {
                personId: person.id,
                roleId: role.id,
            }
        },
        update: {
            personId: person.id,
            roleId: role.id,
        },
        create: {
            personId: person.id,
            roleId: role.id,
        },
    })
}

async function addCourses(names: string[]) {
    let courses = [] as Course[]
    for (let index = 0; index < names.length; index++) {
        const name = names[index];
        courses.push(await prisma.course.upsert({
            where: {
                name: name
            },
            update: {
                name: name
            },
            create: {
                name: name
            }
        }))
    }

    return courses
}