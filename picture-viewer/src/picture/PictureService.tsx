import {Picture} from "./Picture";

export class PictureService {

    getPictures() : Promise<Picture[]> {
        return fetch( `${process.env.SERVER_BASE_URL}/media/pictures`)
            // the JSON body is taken from the response
            .then(res => res.json())
            .then(res => {
                return res as Picture[]
            })
    }

}