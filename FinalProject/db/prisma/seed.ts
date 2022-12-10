import { Person, Prisma, PrismaClient, Role } from '@prisma/client'
const prisma = new PrismaClient()

async function main() {


    const admin = await addRole({ name: "ADMIN" })

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