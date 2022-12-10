import { Person, Prisma, PrismaClient, Role } from '@prisma/client'
const prisma = new PrismaClient()

async function main() {


    const admin = await addRole({ name: "ADMIN" })

    // add the following roles: Company HR, Company Sysadmin, Team Lead, Team Manager, Job Portal User, Job Portal Admin, College Student, College HR, Trainee, Trainer
    const companyHR = await addRole({ name: "COMPANY_HR" })
    const companySysadmin = await addRole({ name: "COMPANY_SYSADMIN" })
    const teamLead = await addRole({ name: "TEAM_LEAD" })
    const teamManager = await addRole({ name: "TEAM_MANAGER" })
    const jobPortalUser = await addRole({ name: "JOB_PORTAL_USER" })
    const jobPortalAdmin = await addRole({ name: "JOB_PORTAL_ADMIN" })
    const collegeStudent = await addRole({ name: "COLLEGE_STUDENT" })
    const collegeHR = await addRole({ name: "COLLEGE_HR" })
    const trainee = await addRole({ name: "TRAINEE" })

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