#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform sampler2D u_texture2;

uniform float u_maskScale;
uniform float u_maskScaleY;
uniform float u_useMask;
uniform float u_extraColor;
uniform vec2 u_maskOffset;

void main()  
{            
	vec2 newCoords = vec2(v_texCoords.x * u_maskScale, v_texCoords.y * u_maskScaleY);
	vec4 mask = texture2D(u_texture2, v_texCoords);
	
	vec4 color = texture2D(u_texture, newCoords);
	
	gl_FragColor = vec4(color.rgb * mask.rgb * u_extraColor, v_color.a * mask.a);
}