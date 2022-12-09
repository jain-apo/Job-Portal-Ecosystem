import { PrismaClient } from '@prisma/client'
const prisma = new PrismaClient()

async function main() {

    let sharunData = {
        firstName: 'Sharun',
        lastName: 'Kumar',
        username: 'sharun',
        phone: '9043133610',
        password: '$2a$12$NReSOnyEHTMjnmYwxLa7be49S4aIsSXi8ROHIeyg/3ZXFwEnmfEee',
        dateOfBirth: new Date('Sun Nov 05 1995 00:04:24 GMT-0500 (Eastern Standard Time)'),
        email: "sharunksplus@gmail.com",
    }

    const sharun = await prisma.person.upsert({
        where: {
            email: "sharunksplus@gmail.com"
        },
        update: sharunData,
        create: sharunData,
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