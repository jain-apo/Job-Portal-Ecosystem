import { Person, Prisma, PrismaClient, Role } from '@prisma/client'
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
    const trainingSiteAdmin = await addRole({ name: "TRAINING_SITE_ADMIN" })

    const sharun = await addUser({
        firstName: 'Sharun',
        lastName: 'Kumar',
        username: 'sharun',
        phone: '9043133610',
        password: '$2a$12$NReSOnyEHTMjnmYwxLa7be49S4aIsSXi8ROHIeyg/3ZXFwEnmfEee',
        dateOfBirth: new Date('Sun Nov 05 1995 00:04:24 GMT-0500 (Eastern Standard Time)'),
        email: "sharunksplus@gmail.com",
    })

    addPersonRole(sharun, admin)
    addPersonRole(sharun, collegeAdmin)
    addPersonRole(sharun, collegeHR)

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
    const company = await addCompany("Google")

    // insert a JobPosting
    const jobPosting = await addJobPosting(company)

    // insert a JobApplication
    const jobApplication = await addJobApplication(sharun, jobPosting)

    // insert a JobCandidate
    const jobCandidate = await addJobCandidate(sharun, jobApplication)

    await addCourses(["Software Developent", "Data Science", "Finance"])
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
    return await prisma.person.upsert({
        where: {
            email: user.email
        },
        update: user,
        create: user,
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

    for (let index = 0; index < names.length; index++) {
        const name = names[index];
        await prisma.course.upsert({
            where: {
                name: name
            },
            update: {
                name: name
            },
            create: {
                name: name
            }
        })
    }
}