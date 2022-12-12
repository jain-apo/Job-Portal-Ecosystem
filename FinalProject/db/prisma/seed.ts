import { Course, Person, Prisma, PrismaClient, Role } from '@prisma/client'
const prisma = new PrismaClient()

async function main() {


    const admin = await addRole({ name: "ADMIN" })

    // add the following roles: Company HR, Company Sysadmin, Team Lead, Team Manager, Job Portal User, Job Portal Admin, College Student, College HR, Trainee, Trainer
    // Company Roles
    const companyHR = await addRole({ name: "COMPANY_HR" })
    const companySysadmin = await addRole({ name: "COMPANY_SYSADMIN" })
    const teamLead = await addRole({ name: "TEAM_LEAD" })
    const teamManager = await addRole({ name: "TEAM_MANAGER" })

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

    // c++ data here

    const csharp = await addTrainingModule("C#")

    // c# data here


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
            yearsOfExperience: 5
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