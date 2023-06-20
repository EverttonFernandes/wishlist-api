const request = require('supertest');
const app = "http://localhost:8080"

describe("Specification with responsibility to ensure the search for products", () => {
    it("should get all products", async () => {
        const response = await request(app).get('/products').expect(200)

        expect(response.body).toEqual(expect.anything())
    })

    it("should get product by id", async () => {
        const response = await request(app).get('/products'.concat("/648c6d36da518f04371792fd")).expect(200)

        expect(response.body).toEqual(expect.anything())
        expect(response.body.name).toEqual('TV')
        expect(response.body.price).toEqual(3000)
    })
})