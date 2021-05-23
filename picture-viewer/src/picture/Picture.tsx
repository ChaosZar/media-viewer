export class Picture {
    id?: number;
    name?: string;
    mediatype?: string;

    isVideo() {
        return this.mediatype?.startsWith("video");
    }

}